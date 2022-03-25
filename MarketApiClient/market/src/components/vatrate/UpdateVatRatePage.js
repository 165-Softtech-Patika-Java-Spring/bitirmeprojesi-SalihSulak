import React from "react";
import Form from "react-bootstrap/Form";
import { Button } from "react-bootstrap";
import ProductTypeCombobox from "../gen/combobox/ProductType/ProductTypeCombobox";
import serialize from "form-serialize";
import VatRateService from "../../api/VatRateService";

class UpdateVatRate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isVatRateUpdated: "",
    };
    this.handlerChange = this.handlerChange.bind(this);
  }
  handlerChange(event) {
    this.setState({ [event.target.name]: event.target.value });

    console.log(this.state);
  }

  handleFormSubmit = (e) => {
    e.preventDefault();
    const vatRate = serialize(e.target, { hash: true });
    this.updateVatRate(vatRate);
  };
  updateVatRate(vatRate) {
    VatRateService.updateVatRate(vatRate)
      .then((response) => this.handleResponse(response))
      .catch((error) => this.handleError(error));
  }
  handleResponse(response) {
    console.log(response);
    this.setState({ isVatRateUpdated: "Vat Rate Successfully Updated" });
  }
  handleError(error) {
    console.log(error);
  }

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-3"></div>
          <div className="col-6">
            <p style={{ color: "green" }}>{this.state.isProductUpdated}</p>

            <Form onSubmit={this.handleFormSubmit}>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Product Type</Form.Label>
                <ProductTypeCombobox fieldName="productType"></ProductTypeCombobox>
              </Form.Group>
              <Form.Group className="mb-3" controlId="vatPercentage">
                <Form.Label>VAT Percentage</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="10"
                  name="vatPercentage"
                />
              </Form.Group>
              <Button variant="primary" type="submit">
                Update
              </Button>
            </Form>
          </div>
          <div className="col-3"></div>
        </div>
      </div>
    );
  }
}

export default UpdateVatRate;
