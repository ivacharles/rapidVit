import React from "react";
import {useNavigate} from "react-router-dom";

import {Button, Card} from "react-bootstrap";

function CardBanner(props) {
    let navigate = useNavigate();

    return(
        <Card style={{ width: '15rem'}} className={"text-center bg-light"}>
            <Card.Img variant="top" src={props.bannerIMG} />
            <Card.Body>
                <Card.Title><span className="text-black fs-5 fw-bolder text-nowrap border-0">{props.bannerTitle}</span></Card.Title>
                <Card.Text>
                    {props.bannerDesc}
                </Card.Text>
                <Button
                    id={"btn-special-rapidvit-color"}
                    className="text-black fs-6 fw-bolder text-nowrap border-0"
                    onClick={() =>{
                        let pathToGo = `/${props.bannerPage}`;
                        navigate("/signin");
                    }}
                >{props.bannerBtn}
                </Button>
            </Card.Body>
        </Card>
    );
}
export default CardBanner;