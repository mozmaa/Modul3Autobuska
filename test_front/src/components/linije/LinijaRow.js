import { Button } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import TestAxios from "../../apis/TestAxios"

export const LinijaRow = (props) => {
   
    console.log(props)
    
    const goToEdit = (pretplata) =>{
        props.editCallback(pretplata)
        navigate('/linija/edit')
    }

    const deletePretplata = (id) => {
        TestAxios.delete('/linije/' + id)
            .then(res => {
                alert ('Uspesno brisanje')
                props.deleteCallback((linije)=>linije.filter(linije => linije.id !== id))
                if(props.linije.length === 1){
                    props.getLinijeCallback(props.pageNo-1)
                }
            }).catch(error => {
                alert('Doslo je do greske prilikom brisanja')
            })
    }

    const navigate = useNavigate()

    const rezervisi = (id, destinacija) => {
        
        const datum = new Date().toISOString()
        const conf = {
                'datumIVremeRezervacije': datum,
                'linijaId': id,
                'destinacija': destinacija
        }

        console.log(conf)
        
        TestAxios.post('/rezervacije' , conf)
            .then(res => {
                alert('Uspesna rezervacija')
                props.getLinijeCallback(props.pageNo)
            }).catch(error => {
                alert('Doslo je do greske')
            })
    }

    return (
        <tr key={props.linija.id}>
            <td>{props.linija.prevoznikNaziv}</td>
            <td>{props.linija.destinacija}</td>
            <td>{props.linija.brojMesta}</td>
            <td>{props.linija.vremePolaska}</td>
            <td>{props.linija.cenaKarte}</td>
            {props.loginInfo?.isAdmin&&
                 <td><Button disabled={props.linija.brojMesta === 0 } onClick={() => rezervisi(props.linija.id , props.linija.destinacija)}>Rezervisi</Button></td>
                 }
            {props.loginInfo?.isAdmin && <td><Button variant="warning" onClick={() => goToEdit(props.linija)} >Edit</Button></td>}
            {props.loginInfo?.isAdmin && <td><Button variant="danger" onClick={() => deletePretplata(props.linija.id)}>Delete</Button></td>}
        </tr>
    )
}