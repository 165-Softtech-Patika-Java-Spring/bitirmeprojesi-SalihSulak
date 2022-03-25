import React from "react";
import ProductService from "../../api/ProductService";
import Form from 'react-bootstrap/Form';
import { Button } from "react-bootstrap";
import ProductTypeCombobox from "../gen/combobox/ProductType/ProductTypeCombobox";
import serialize from 'form-serialize';

class UpdateProduct extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            isProductUpdated:""
        }
        this.handlerChange = this.handlerChange.bind(this);

    }
    handlerChange(event){
        
        this.setState({[event.target.name]: event.target.value})

        console.log(this.state)
    }

    handleFormSubmit = (e) => {

        e.preventDefault();
        const product = serialize(e.target, { hash: true })
        console.log(product);
        const url = window.location.href;
        const params = url.split('/')
        console.log(params);
        const id = params[5];
        product.id = id;
        this.updateProduct(product);

    }
    updateProduct(product){
        ProductService.updateProduct(product)
        .then(response => this.handleResponse(response))
        .catch(error => this.handleError(error))
    }
    handleResponse(response) {
        console.log(response)
        this.setState({isProductUpdated: "Product Successfully Updated"})
    }
    handleError(error){

      console.log(error);
  }

    render(){
        return (
          <div className="container">
              <div className="row">
                  <div className="col-3"></div>
                  <div className="col-6">
                  <p style={{color:'green'}}>{this.state.isProductUpdated}</p>

            <Form onSubmit={this.handleFormSubmit}>
  <Form.Group className="mb-3" controlId="productName">
    <Form.Label>Product Name</Form.Label>
    <Form.Control type="text" placeholder="Apple" name="productName" />
  </Form.Group>

  <Form.Group className="mb-3" controlId="formBasicPassword">
    <Form.Label>Product Type</Form.Label>
    <ProductTypeCombobox fieldName="productType"></ProductTypeCombobox>
  </Form.Group>
  <Form.Group className="mb-3" controlId="priceWithoutVat">
    <Form.Label>Price Without VAT</Form.Label>
    <Form.Control type="text" placeholder="40" name="priceWithoutVat"/>
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

export default UpdateProduct;