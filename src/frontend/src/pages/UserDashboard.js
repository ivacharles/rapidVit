import React, {useState} from "react";
import {Tabs, Tab, Container, Form, Navbar, Nav, Col, Row} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import rapidVitLogo from "../images/rapidvit_logo.png";
import MyListingTab from "../components/MyListingTab";
import MyAccountSettingTab from "../components/MyAccountSettingTab";




function UserDashboard() {
    return(
        <Container className="" fluid={"md"}>
            <UserDashboardMenu />
            <UserDashboardSubMenu />
        </Container>
    );

}

function UserDashboardMenu() {
    return(
        <Navbar className={"sticky-top nav-bg-color px-1  py-0 h-100"}>
            <Navbar.Brand href="/" className={""} as={Col} lg={"2"}>
                <NavLink to="/" className="">
                <img
                    src={rapidVitLogo}
                    width="120"
                    height="30"
                    className="d-inline-block align-top"
                    alt="RapidVit logo"
                />
                </NavLink>
            </Navbar.Brand>
            <div className="vr text-black" />
            <Nav className="ms-auto">
                <div className="vr text-black" />
                <NavLink to="/signin" className="nav-link  text-black fw-bolder text-nowrap">Sign out</NavLink>
            </Nav>
        </Navbar>
    );
}

function UserDashboardSubMenu() {
    return (

        <Container fluid={"true"} className={"pt-5 bg-light"}>
            <Tabs defaultActiveKey="listings" id="uncontrolled-tab-example" className={" title-color tab-a-color mb-3 "}>
                <Tab eventKey="listings" className={""}  title="My listings" >
                    <MyListingTab />
                </Tab>
                <Tab eventKey="account-setting" title="My account" >
                    <MyAccountSettingTab />
                </Tab>
            </Tabs>
        </Container>
    );
}
export default UserDashboard;