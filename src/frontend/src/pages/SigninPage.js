import React, {useRef, useState, useEffect, useContext} from "react";
import {Button, Container, Form} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import AuthContext from "../context/AuthProvider";
import axios from "../calls/axios";

const LOGIN_URL = "/user/signin";

function SigninPage() {

    const {setAuth} = useContext(AuthContext);

    const userRef = useRef(); //this is to set focus where error in the form
    const errorRef = useRef();//this is to set focus on the error msg for user to see

    const [username, setUsername] = useState(""); // form username input
    const [password, setPassword] = useState("");
    const [errorMsg, setErrorMsg] = useState(""); // show error

    useEffect(() =>{
        userRef.current.focus();
    }, [])

    //empty out the error msg when error fixes it
    useEffect(() => {
        setErrorMsg("");
    }, [username, password])


    const handleChange = async (e) => {
        console.log("----------------------test-------------------");
        console.log("username: "+username);
        console.log("pwd: "+password);
        const userCredential = JSON.stringify({username, password});
        console.log("credential: "+userCredential);

        e.preventDefault();
        try {
            const response = await axios.post(
                LOGIN_URL,
                JSON.stringify({username, password}),
                {
                    headers: {"Content-Type": "application/json"},
                    withCredentials: true
                }
            );
            const token = response?.data;
            setAuth({username,password, token})
            console.log("----------------------response-------------------");
            console.log(response.data);
            setUsername("");
            setPassword("");
        } catch (error) {
            if(!error?.response){
                console.log("----------------------response-------------------");
                console.log(error?.response)
                setErrorMsg("No Server Response");
            }else if(error?.response?.status === 403){
                setErrorMsg("Unauthorized");
            }else if(error?.response?.status === 401){
                setErrorMsg("All fields are required");
            }else {
                setErrorMsg("Login Failed");
            }
            errorRef.current.focus();
        }
    }

    return(
        <Container  id="account-container" className="bg-light p-5">
            <h1 className={"pt-4"}>Welcome Back</h1>
            <h4 className={"pb-4"}>Log in to your account</h4>
            <p ref={errorRef} aria-live={"assertive"} className={"text-danger"}>{errorMsg}</p>
            <Form onSubmit={handleChange}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control
                        type="email"
                        aria-autocomplete={"none"}
                        ref = {userRef}
                        onChange={(e) => {setUsername(e.target.value)}}
                        value={username}
                        placeholder="Enter email"
                        required>
                    </Form.Control>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        ref={userRef}
                        onChange={(e) => {setPassword(e.target.value)}}
                        value={password}
                        required
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox"  label="Remember me" />
                </Form.Group>
                <NavLink to="/reset-pwd" className="nav-link  text-info text-end fw-bolder text-nowrap">Forgot Password?</NavLink>
                <Button variant="dark" type="submit" className={"w-100 my-3"}>
                    Sign in
                </Button>
                <NavLink to="/signup" className="nav-link  text-info text-end fw-bolder text-nowrap">
                    <span className={"text-dark"}>Don't have an account?</span>
                    Register Now
                </NavLink>
            </Form>
        </Container>
    );
}
export default SigninPage;