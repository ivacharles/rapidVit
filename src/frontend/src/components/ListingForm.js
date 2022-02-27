import React, {useState} from "react";
import {Button, Container, Form} from "react-bootstrap";
import {NavLink} from "react-router-dom";

function ListingForm() {
    return(
        <Container  id="account-container" className="bg-light p-5">
            <h4 className={"py-4"}>Create an account</h4>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicFull">
                    <Form.Label>First name</Form.Label>
                    <Form.Control type="text" placeholder="Enter your full name" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email address" />
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicFull">
                    <Form.Label>Phone number</Form.Label>
                    <Form.Control type="text" placeholder="Enter last name" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" checked={"true"} label="By signup with us you agree to rapidVit's 'Terms and Conditions' and 'Privacy Policy.'" />
                </Form.Group>
                <Button variant="dark" type="submit" className={"w-100 my-3"}>
                    Sign Up
                </Button>
                <NavLink to="/signin" className="nav-link  text-info text-end fw-bolder text-nowrap"><span className={"text-dark"}>Already have account?</span> Log in</NavLink>
            </Form>
        </Container>
    );
}
export default ListingForm;