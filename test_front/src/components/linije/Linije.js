import { Col, FormCheck, FormLabel, Stack , Button} from "react-bootstrap"
import { Table } from "react-bootstrap"
import { LinijaRow } from "./LinijaRow"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import TestAxios from "../../apis/TestAxios"
import { Row } from "react-bootstrap"
import FormCheckInput from "react-bootstrap/esm/FormCheckInput"
import { Search } from "./Search"

export const Linije = (props) => {

    const[linije, setLinije] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [pageCount, setPageCount] = useState(0)
    const [checked, setChecked] = useState(false)
    const [searchParams, setSearchParams] = useState({})
    const[prevoznici, setPrevoznici] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        getLinije(0)
        getPrevoznici()
    }, [])

    const getLinije = (newPageNo) => {

        const conf = {
            params: {
                ...searchParams,
                pageNo: newPageNo
            }
        }
        TestAxios.get('/linije', conf)
            .then(res => {
                console.log(res)
                setLinije(res.data)
                setPageCount(Number(res.headers['total-pages']))
                setPageNo(newPageNo)
                
            }).catch(error => {
                console.log(error)
                alert('Doslo je do greske')
            })       
    }

    const getPrevoznici = () => {
        TestAxios.get('/prevoznici')
            .then(res => {
                setPrevoznici(res.data)
            }).catch(error => {
                alert('Doslo je do greske!')
            })
    }

    const renderLinije = () => {
        return linije.map((linija,index) => {
            return (
            <LinijaRow key={index} linija={linija} editCallback={props.callback} 
                        loginInfo={props.loginInfo} linije={linije} deleteCallback={(linije) => setLinije(linije)}
                        pageNo = {pageNo} getLinijeCallback={(pageNo) => {getLinije(pageNo)}} >
            </LinijaRow>
            )
        })
    }

    const goToAdd = () => {
        navigate('/linije/dodavanje')
    }

    const goToTop3Mesta = () => {
        navigate('/linije/topMesta')
    }

    return (
        <Col>
            <Row><h1>Linije</h1></Row>
            <FormCheck>
                <FormCheckInput name="pretraga" onChange={(e) => setChecked(e.target.checked)}></FormCheckInput>
                <FormLabel htmlFor="pretraga">Prikazi pretragu</FormLabel>
            </FormCheck>
            {checked && <Search searchParamsCallback={(searchParams) => setSearchParams(searchParams)} 
                getLinijeCallback={(firstPage) => getLinije(firstPage)} searchParams={searchParams} prevoznici={prevoznici}/>}
            <br></br>
            <Row><Col>
                <Table striped bordered hover bg="dark" variant="dark" id="linije=table">
                    <thead>
                        <tr>
                            <th>Naziv prevoznika</th>
                            <th>Destinacija</th>
                            <th>Broj mesta</th>
                            <th>Vreme polaska</th>
                            <th>Cena karte</th>
                            
                            {(props.loginInfo?.isAdmin || props.loginInfo?.isUser) && <th></th> }
                            {props.loginInfo?.isAdmin  && <th></th> }
                            {props.loginInfo?.isAdmin  && <th></th> }
                        </tr>
                    </thead>
                    <tbody>
                        {renderLinije()}
                    </tbody>
                </Table>
            </Col></Row>
            <Stack direction="horizontal" gap={3}>
                {props.loginInfo?.isAdmin ? <Button className="button button-navy" onClick={() => goToAdd()}>Add</Button> : <></>}
                <Button onClick={() => goToTop3Mesta()}>Top 3 slobodna mesta</Button>
                <Button>Top 3 cene</Button>
                <Button className="ms-auto" disabled={pageNo === 0} onClick={() => getLinije(pageNo - 1)}>Prev</Button>
                {linije.length === 0 ? pageNo : pageNo + 1}/{pageCount}
                <Button disabled={pageNo === pageCount - 1} onClick={() => getLinije(pageNo + 1)}>Next</Button>
            </Stack>
        </Col>
    )
}