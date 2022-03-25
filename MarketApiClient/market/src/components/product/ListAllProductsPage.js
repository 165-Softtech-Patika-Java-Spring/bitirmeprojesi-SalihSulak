import React from "react";
import Table from 'react-bootstrap/Table';
import ProductService from "../../api/ProductService";

class ListStats extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            data: [],
        }
        this.handlerChange = this.handlerChange.bind(this);

    }
    componentDidMount() {
        this.listAll();
    }
    listAll(){
        ProductService.listAll()
        .then(response => this.handleResponse(response))
        .catch(error => this.handleError(error))
    }
    handleResponse(response) {
        console.log(response)
        this.setState({ data: response.data.data })
    }
    handleError(error){

      console.log(error);
  }
    handlerChange(event){
        
        this.setState({[event.target.name]: event.target.value})

        console.log(this.data)
    }
    render(){
        return (
          <div className="container">
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Product Name</th>
                  <th>Product Type</th>
                  <th>Price Without VAT</th>
                  <th>Price With VAT</th>
                </tr>
              </thead>
              <tbody>
                {this.state.data.map((data) => (
                  <tr>
                    <td><a style={{textDecoration:'none',color:'dark-blue'}} href={'/products/update/' + data.id}>{data.productName} (Press for update product)</a></td>
                    <td>{data.productType}</td>
                    <td>{data.priceWithoutVat}</td>
                    <td>{data.priceWithVat}</td>

                  </tr>
                ))}
              </tbody>
            </Table>
          </div>
        );
    }
}

export default ListStats;