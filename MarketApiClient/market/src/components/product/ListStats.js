import React from "react";
import Table from 'react-bootstrap/Table';
import ProductService from "../../api/ProductService";

class ListStats extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            stats: [],
        }
        this.handlerChange = this.handlerChange.bind(this);

    }
    componentDidMount() {
      const queryParams = new URLSearchParams(window.location.search);
      const productType = queryParams.get('productType');
        this.listStats(productType);
    }
    listStats(productType){
        ProductService.listStats(productType)
        .then(response => this.handleResponse(response))
    }
    handleResponse(response) {
        console.log(response)
        this.setState({ stats: response.data.data })
    }
    handlerChange(event){
        
        this.setState({[event.target.name]: event.target.value})

        console.log(this.state)
    }
    render(){
        return (
          <div className="container">
            <Table striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th>Product Type</th>
                  <th>VAT Percentage</th>
                  <th>Min Price</th>
                  <th>Max Price</th>
                  <th>Average Price</th>
                  <th>Number Of Product</th>
                </tr>
              </thead>
              <tbody>
                {this.state.stats.map((stat) => (
                  <tr>
                    <td>{stat.productType}</td>
                    <td>{stat.vat_percentage}</td>

                    <td>{stat.minPrice}</td>
                    <td>{stat.maxPrice}</td>
                    <td>{stat.averagePrice}</td>
                    <td>{stat.productCount}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </div>
        );
    }
}

export default ListStats;