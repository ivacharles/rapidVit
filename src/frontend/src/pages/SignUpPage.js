import React, {useEffect, useRef, useState} from "react";
import {Button, Col, Container, Form, Navbar} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {useForm} from "react-hook-form";
import axios from "axios";

const SIGN_URL = "user/signup"
const USER_EMAIL_REGEX =/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
const USER_PHONE_REGEX =/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/
const USER_PWD_REGEX = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/


function SignUpPage() {

    const [errorMsg, setErrorMsg] = useState(null);
    const {register, handleSubmit,formState, reset, formState: {errors, isSubmitSuccessful}} = useForm({
        mode: "onChange",
        defaultValues:{
            "userFName":"",
            "userLName": "",
            "userPhoneNumber":"",
            "userEmail":"",
            "userPwd":""
        }
    });

    useEffect(() =>{
        if(formState.isSubmitSuccessful){
            reset();
        }
    }, [isSubmitSuccessful, reset]);

    const onFormSubmission = async (data, event) => {
        event.preventDefault();

        try {
            console.log("----------------------trying-------------------");
            console.log(data);
            const response = await axios.post(
                SIGN_URL,
                JSON.stringify(data),
                {
                    headers: {"Content-Type": "application/json"},
                    withCredentials: true
                }
            );
            console.log("----------------------response while trying-------------------");
            console.log(response.data);
            setErrorMsg(response.data?.userMessage);
        } catch (error) {
            if(!error?.response){
                console.log("----------------------error-------------------");
                console.log(error?.response)
            }else if(error?.response?.status === 401){
                setErrorMsg("Unauthorized");
            }else if(error?.response?.status === 400){
                setErrorMsg("All fields are required");
            }else {
                setErrorMsg("There was a problem with your Registration ");
            }
        }
    }

    return(
        <Container  id="account-container" className="bg-light p-5">
            <h4 className={"py-4"}>Create an account</h4>
            {errorMsg!==null?<span className={"text-danger pb-4"}>{errorMsg}</span>:""}
            <Form onSubmit={handleSubmit(onFormSubmission)}>

                <Form.Group className="mb-3" controlId="formBasicFName">
                    <Form.Label>First name</Form.Label>
                    <Form.Control type="text" placeholder="Enter your first name" {...register('userFName', { required: true, min:3 })}/>
                    {errors.userFName && <span className='text-danger'>Your first name is required</span>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicLName">
                    <Form.Label>First name</Form.Label>
                    <Form.Control type="text" placeholder="Enter your last name"{...register('userLName', { required: true, min:3 })}/>
                    {errors.userLName && <span className='text-danger'>Your last name is required</span>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPhoneNumber">
                    <Form.Label>Phone number</Form.Label>
                    <Form.Control type="text" placeholder="Enter your phone number"{...register('userPhoneNumber', { required: true , pattern: USER_PHONE_REGEX})}/>
                    {errors.userPhoneNumber && <span className='text-danger'>Your phone number is required</span>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control
                        type="email" placeholder="Enter your email address" {...register('userEmail', { required: true , pattern: USER_EMAIL_REGEX})}/>
                        {errors.userEmail && <span className='text-danger'>Your email is required</span>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password" placeholder="Password" {...register('userPwd', { required: true , pattern: USER_PWD_REGEX})}/>
                        {errors.userPwd && <span className='text-danger'>Your password is required</span>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" defaultChecked  label="By signup with us you agree to rapidVit's 'Terms and Conditions' and 'Privacy Policy.'" />
                </Form.Group>

                <Button variant="dark" type="submit" className={"w-100 my-3"}>
                    Sign Up
                </Button>
                <NavLink to="/signin" className="nav-link  text-info text-end fw-bolder text-nowrap"><span className={"text-dark"}>Already have account?</span> Log in</NavLink>
            </Form>
        </Container>
    );
}



export default SignUpPage;