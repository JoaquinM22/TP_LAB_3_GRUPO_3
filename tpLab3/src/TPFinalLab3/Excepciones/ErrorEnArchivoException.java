package TPFinalLab3.Excepciones;

import java.io.IOException;

public class ErrorEnArchivoException extends IOException
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = 5889881597107109149L;

    /** CONSTRUCTOR **/
    public ErrorEnArchivoException(int n)
    {
        if(n == 1)
        {
            System.out.println("Error al intentar escribir en el archivo");
        }else if(n == 2)
        {
            System.out.println("Error al intentar leer el archivo");
        }
        printStackTrace();
    }
}
