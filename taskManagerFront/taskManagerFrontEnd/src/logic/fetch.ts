import type { UserData } from "../types";


export const registrarUsuario = async (userData: UserData) => {
    try {
        const response = await fetch("http://localhost:8080/register", {
            method: "post",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userData),
        });
        if (!response.ok) {
            const errorBody = await response.json(); 
            throw {
                message:errorBody.message,
                status: response.status,
                body: errorBody
            };
        }
        return await response.json();
    } catch (error:any) {
        console.error(error.message);
        throw error;
    }
}