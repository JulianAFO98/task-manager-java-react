import { useNavigate } from "react-router-dom";

const VALUES = {
    "ingresar":"Ingresar",
    "inicio":"Inicio",
    "registrarse":"Registrarse"
} as const ;

type ParamKey = keyof typeof VALUES;

type HeaderProps = {
    params: ParamKey[];
}


export function Header({ params }: HeaderProps) {
    const navigate = useNavigate();
    return (
        <>
            <header>
                <nav>
                    <ul>
                        {params.map((item, index) => (
                            <li key={index}  onClick={()=>navigate(item === "inicio" ? "/" : `/${item}`)}>{VALUES[item]}</li>
                        ))}
                    </ul>
                </nav>
            </header>
        </>
    )
}