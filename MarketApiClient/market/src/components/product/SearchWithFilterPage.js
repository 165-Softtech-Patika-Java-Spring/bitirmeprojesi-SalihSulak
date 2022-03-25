import React from "react";
import ProductService from "../../api/ProductService";
import Form from 'react-bootstrap/Form';
import { Button } from "react-bootstrap";
import ProductTypeCombobox from "../gen/combobox/ProductType/ProductTypeCombobox";
import serialize from 'form-serialize';
import { FormLabel } from "react-bootstrap";
import { Navigate } from "react-router-dom";

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
                <p style={{ color: "green" }}>{this.state.isProductUpdated}</p>
                <Form
                  style={{ border: "4px solid", padding: 10 }}
                >
                  <FormLabel style={{ color: "red", fontSize: 20 }}>
                    List All Products
                  </FormLabel>

<br></br>
                  <Button variant="primary" type="submit">
                    <a href="/products/all" style={{color:'white', textDecoration:'none'}}>
                    Search
                    </a>
                  </Button>
                </Form>
                <Form action="/products/productType"
                  style={{ border: "4px solid", padding: 10 }}
                >
                  <FormLabel style={{ color: "red", fontSize: 20 }}>
                    Search By Product Type
                  </FormLabel>

                  <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Product Type</Form.Label>
                    <ProductTypeCombobox fieldName="productType"></ProductTypeCombobox>
                  </Form.Group>
                  <Button variant="primary" type="submit">
                    Search
                  </Button>
                </Form>
                <Form
                  action="/products/price"
                  style={{ border: "4px solid", padding: 10 }}
                >
                  <FormLabel style={{ color: "red", fontSize: 20 }}>
                    Search By Price
                  </FormLabel>

                  <Form.Group className="mb-3" controlId="minPrice">
                    <Form.Label>Minimum Price</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="40"
                      name="minPrice"
                    />
                  </Form.Group>
                  <Form.Group className="mb-3" controlId="maxPrice">
                    <Form.Label>Maximum Price</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="40"
                      name="maxPrice"
                    />
                  </Form.Group>
                  <Button variant="primary" type="submit">
                    Search
                  </Button>
                </Form>

                <Form action="/products/listStats"
                  style={{ border: "4px solid", padding: 10 }}
                >
                  <FormLabel style={{ color: "red", fontSize: 20 }}>
                    Get Product Type Stats
                  </FormLabel>

                  <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Product Type</Form.Label>
                    <ProductTypeCombobox fieldName="productType"></ProductTypeCombobox>
                  </Form.Group>
                  <Button variant="primary" type="submit">
                    Search
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