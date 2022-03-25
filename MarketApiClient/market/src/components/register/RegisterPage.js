import React from "react";
import Form from 'react-bootstrap/Form';
import { Button, Col, Row } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthenticationService from "../../api/AuthenticationService";
import { useHistory } from 'react-router-dom';
import Alert from 'react-bootstrap/Alert'

class RegisterPage extends React.Component{

    constructor(props){
        super(props);
        
        this.state = {
          name : "",
          surname : "",
          username : "",
          password : "",
          isSuccess : false
        }

        this.handlerChange = this.handlerChange.bind(this)

    }

    handlerChange(event){
      //console.log(event)
      this.setState({[event.target.name]: event.target.value})

      //console.log(this.state)
  }

  handleFormSubmit = (e) => {

      e.preventDefault();

      AuthenticationService.register(this.state.name,this.state.surname,this.state.username,this.state.password)
          .then(response => this.handleResponse(response))
          .catch(error => this.handleError(error))

  }

  handleResponse(response){
      console.log(response);
      console.log(response.data.success)
      if(response.data.success==true){
        this.setState({isSuccess : true})
      }
  }

  handleError(error){

      console.log(error);
  }
    render(){

      const isSuccess = this.state.isSuccess;
      if (isSuccess) {
        return (
<Alert variant="success">
  <Alert.Heading>Registered successfully</Alert.Heading>
  <p>
    We are very happy to see you among us. 
  </p>
  <hr />
  <p className="mb-0">
    Click login button to login
  </p>
<a href="/login">login</a>
</Alert>
        );
      }
        return (
          <div className="container">
              <Row>
                  <Col></Col>
                  <Col>
              <Form onSubmit={this.handleFormSubmit}>
                <Form.Group className="mb-3" controlId="name">
                  <Form.Label>Name</Form.Label>
                  <Form.Control type="text" placeholder="John" name="name" value={this.state.name} onChange={this.handlerChange}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="surname">
                  <Form.Label>Surname</Form.Label>
                  <Form.Control type="text" placeholder="Doe" name="surname" value={this.state.surname} onChange={this.handlerChange}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="username">
                  <Form.Label>Username</Form.Label>
                  <Form.Control type="text" placeholder="Username" name="username" value={this.state.username} onChange={this.handlerChange}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" placeholder="Password" name="password" value={this.state.password} onChange={this.handlerChange}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </Form>
              </Col>
              <Col></Col>
              </Row>
          </div>
        );
    }
}

export default RegisterPage;

