import React from "react";
import ProductTypeCombobox from "../gen/combobox/ProductType/ProductTypeCombobox";
import serialize from 'form-serialize';
import ProductService from "../../api/ProductService";

class AddProductPage extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            isProductSaved:""
        }

        this.handlerChange = this.handlerChange.bind(this)

    }
    handlerChange(event){
        
        this.setState({[event.target.name]: event.target.value})

        console.log(this.state)
    }

    handleFormSubmit = (e) => {

        e.preventDefault();
        const product = serialize(e.target, { hash: true })
        console.log(product);
        this.save(product);

    }
    save(product){
        ProductService.add(product)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
    }

    handleResponse(response){

        console.log(response);
        this.message();
    }

    message(){
        this.setState({isProductSaved: "Product Successfully Saved"})
    }

    handleError(error){

        console.log(error);
    }

    render(){
        return(

            <div className="container">
                <div className="text-center mt-5">
                    <div className="row col-md-4 offset-md-4">
                        <form className="form-signin" onSubmit={this.handleFormSubmit}>

                            <h1 className="h3 mb-3 font-weight-normal">Add Product</h1>
                            <p style={{color:'green'}}>{this.state.isProductSaved}</p>

                            <div style={{padding:20}}>
                            <label htmlFor="inputProductName" className="sr-only">Product Name</label>
                            <input
                                type="text"
                                id="inputProductName"
                                className="form-control"
                                placeholder="Apple"
                                required=""
                                autoFocus=""
                                name="productName"
                            />
                        </div>
                        <div style={{padding:20}}>
                        <label htmlFor="inputProductType">Product Type</label>
                        <ProductTypeCombobox
                            fieldName="productType"
                        ></ProductTypeCombobox>
                        </div>
                        <div style={{padding:20}}>
                            <label htmlFor="inputPrice" className="sr-only">Price Without VAT</label>
                            <input
                                type="number"
                                id="inputPrice"
                                className="form-control"
                                placeholder="30"
                                required=""
                                name="priceWithoutVat"
                            />
                        </div>
                            <div style={{padding:20}}>
                            <input type="submit" className="btn btn-success btn-block" value="Save Product" />
                            <p className="mt-5 mb-3 text-muted">© 2022</p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
      

        );
    }
}
export default AddProductPage;