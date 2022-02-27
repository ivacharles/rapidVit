import React, {useState} from "react";
import {Button, Card, Col, Container, Form, FormControl, FormGroup, FormLabel} from "react-bootstrap";
import {NavLink} from "react-router-dom";

function SigninPage() {
    const [formInput, setFormInput] = useState(null);

    return(
        <Container  id="account-container" className="bg-light p-5">
            <h1 className={"py-4"}>Reset Password</h1>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" />
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>
                 <Button variant="dark" type="submit" className={"w-100 my-3"}>
                    Reset password
                </Button>
                <NavLink to="/signin" className="nav-link  text-info text-end fw-bolder text-nowrap">Back to Log in</NavLink>
            </Form>
        </Container>
    );
}
export default SigninPage;