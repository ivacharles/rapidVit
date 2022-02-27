import React from "react";
import {NavLink} from "react-router-dom";
import {
    Button,
    Container,
    Form,
    FormControl,
    Nav,
    Navbar,
    NavDropdown,
} from "react-bootstrap";
import rapidVitLogo from "../images/rapidvit_logo.png";
function Menu() {
    return (
        <>
            <Navbar className="nav-bg-color"  >
                <Container>
                    <Navbar.Brand href="/">
                        <img
                            src={rapidVitLogo}
                            width="120"
                            height="50"
                            className="d-inline-block align-top"
                            alt="RapidVit logo"
                        />
                    </Navbar.Brand>
                    <Form className="d-flex ms-auto  w-50 ">
                        <FormControl
                            type="search"
                            placeholder="Search"
                            className="text-black fs-5 fw-bolder border-dark rounded-0 d-none d-sm-none d-md-none d-lg-inline d-xl-inline"
                            aria-label="Search"
                        />
                        <Button variant="outline-dark" className="text-black fs-5 fw-bolder rounded-0 d-none d-sm-none d-md-none d-lg-inline d-xl-inline">Search</Button>
                    </Form>
                    <Nav className="ms-auto">
                        <NavLink to="/create-listing" className="nav-link  text-black fs-5 fw-bolder text-nowrap">Create a listing</NavLink>
                        <div className="vr text-black" />
                        <NavLink to="/signin" className="nav-link text-black fs-5 fw-bolder" >Account</NavLink>
                        <div className="vr text-black" />
                        <NavDropdown title="English" className="text-black fs-5 fw-bolder" id="navbarScrollingDropdown">
                            <NavDropdown.Item href="#action3" className=" fs-5 fw-bolder">Creole</NavDropdown.Item>
                            {/*<NavDropdown.Divider />*/}
                            {/*<NavDropdown.Item href="#action5">*/}
                            {/*    Something else here*/}
                            {/*</NavDropdown.Item>*/}
                        </NavDropdown>
                    </Nav>
                </Container>
            </Navbar>
        </>
    );
}

export default Menu;
