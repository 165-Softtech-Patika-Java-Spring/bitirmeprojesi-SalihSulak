import React from "react";
import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";

class Menu extends React.Component {

    constructor(props) {
        super(props);

        this.handleLogout = this.handleLogout.bind(this)

    }

    handleLogout(){
        this.props.logout();
    }

    render() {

        return (
            <div className="col-md-6 offset-md-3 ">
                <Navbar bg="light" expand="lg">
                    <Container>
                        <Navbar.Brand href="/">Softtech</Navbar.Brand>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="me-auto">
                                <NavDropdown title="Product" id="products">
                                    <NavDropdown.Item href="/products/add">Add Product</NavDropdown.Item>
                                    <NavDropdown.Divider />
                                    <NavDropdown.Item href="/products/all">All Products</NavDropdown.Item>
                                    <NavDropdown.Divider />
                                    <NavDropdown.Item href="/">Search Products With Filter</NavDropdown.Item>
                                </NavDropdown>

                                <NavDropdown title="Profile" id="basic-nav-dropdown">

                                    <NavDropdown.Item href="/register">Add User</NavDropdown.Item>

                                    <NavDropdown.Divider />
                                    {!this.props.isLoggedOn && < NavDropdown.Item href="/login">Login</NavDropdown.Item>}
                                    {this.props.isLoggedOn && <NavDropdown.Item href="/" onClick={this.handleLogout}>Logout</NavDropdown.Item>}
                                </NavDropdown>

                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
            </div>
        );
    }
}

export default Menu;