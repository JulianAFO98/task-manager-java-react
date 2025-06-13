import { useState, type FormEvent } from "react";
import { Header } from "../components/Header";
import { registrarUsuario } from "../logic/fetch";
import type { UserData } from "../types";



export  function  Register() {
   
   const [mensaje,setMensaje]  = useState<null | string>(null);

   const mostrarMensajeExito = () => {
        setMensaje("Exito al registrarse");
   }

    const mostrarMensajeFallo = (error:any) => {
        setMensaje("Fallo al registrarse");
   }

    const sendForm = async (e : FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.target as HTMLFormElement;
        const formData = new FormData(form);
        const formObject = Object.fromEntries(formData.entries());
        const finalObject:UserData = {
            username:formObject.username as string,
            email:formObject.email as string,
            password:formObject.password as string,
            
        };
        try {
            const response =  await registrarUsuario(finalObject);
            console.log(response);
            mostrarMensajeExito();
        } catch (error) {
            mostrarMensajeFallo(error);
        }
    }
    
    //ADD VALIDATIONS

    return (
        <>
            <Header params={["inicio","ingresar"]}/>
            <main className="reg-main">
             {mensaje && (
                <p>{mensaje}</p>
             )}
             <form method="post" onSubmit={sendForm} >
                <label htmlFor="username">Nombre de usuario</label>
                <input type="text" id="username" name="username" required/>
                <label htmlFor="email">Email</label>
                <input type="email" id="email" name="email" required/>
                <label htmlFor="password">Contraseña</label>
                <input type="password" id="password" name="password" />
                <label htmlFor="repeat-password">Repetir contraseña</label>
                <input type="password" id="repeat-password" name="password2" />
                <input type="submit" value="Registrarse"/>
             </form>
            </main>
        </>
    )
}