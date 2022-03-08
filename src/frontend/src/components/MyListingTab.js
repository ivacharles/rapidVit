import React from "react";
import {
    Container,
    Nav,
    Navbar, Tab, Tabs,
} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {AiFillCaretRight} from "react-icons/ai";
import MyAccountSettingTab from "./MyAccountSettingTab";
import ListingForm from "../pages/ListingForm";

function MyListingTab() {
    return (
        <Container className={""}>
            <Tabs defaultActiveKey="create-listings" id="uncontrolled-sub-tab-example" className={" title-color tab-a-color mb-4 "}>
                <Tab eventKey="create-listings" className={""}  title="Kreye yon post" >
                    <ListingForm />
                </Tab>
                <Tab eventKey="modify-listing" title="Modifye yon post" >
                    {/*<MyAccountSettingTab />*/}
                </Tab>
            </Tabs>
        </Container>
    );
}

export default MyListingTab;
