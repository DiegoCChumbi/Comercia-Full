using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServiciosEmail
{
    public class Cuerpo
    {
        public string ObtenerCuerpoCorreo(string usuario, string contrasenha)
        {
            // Plantilla de correo HTML
            // Plantilla de correo HTML con estilo actualizado
            string plantilla = @"
                <!DOCTYPE html>
                <html lang='es'>
                <head>
                    <meta charset='UTF-8'>
                    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                    <title>Credenciales de Acceso a Comerzia</title>
                </head>
                <body style='margin: 0; padding: 0; background-color: #f9f9f9; font-family: Arial, sans-serif;'>
                    <table width='100%' cellpadding='0' cellspacing='0' border='0' bgcolor='#f9f9f9' style='margin: 0; padding: 0; width: 100%; font-family: Arial, sans-serif;'>
                        <tr>
                            <td align='center'>
                                <table width='600' cellpadding='0' cellspacing='0' border='0' bgcolor='#ffffff' style='margin: 30px 0; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden;'>
                                    <!-- Header -->
                                    <tr>
                                        <td align='center' bgcolor='#4CAF50' style='padding: 20px; color: #ffffff; font-size: 24px; font-weight: bold;'>
                                            Credenciales de Acceso a Comerzia
                                        </td>
                                    </tr>
                                    <!-- Body -->
                                    <tr>
                                        <td style='padding: 20px; color: #444444; font-size: 16px; line-height: 1.6;'>
                                            <p style='margin: 0 0 15px 0; font-size: 18px; font-weight: bold; color: #4CAF50;'>Hola,</p>
                                            <p style='margin: 0 0 15px 0;'>Te hemos registrado como usuario en la plataforma **Comerzia**. A continuación, te proporcionamos tus credenciales de acceso:</p>
                                            <!-- Credentials Box -->
                                            <table width='100%' cellpadding='10' cellspacing='0' border='0' bgcolor='#f8f8f8' style='border: 1px solid #e0e0e0; border-radius: 5px; margin: 15px 0;'>
                                                <tr>
                                                    <td style='font-weight: bold;'>Usuario:</td>
                                                    <td>{usuario}</td>
                                                </tr>
                                                <tr>
                                                    <td style='font-weight: bold;'>Contraseña:</td>
                                                    <td>{contrasenha}</td>
                                                </tr>
                                            </table>
                                            <!-- End Credentials Box -->
                                            <p style='margin: 0 0 15px 0;'>Te recomendamos cambiar tu contraseña después de acceder por primera vez para garantizar la seguridad de tu cuenta.</p>
                                            <p style='margin: 0 0 15px 0;'>Si tienes algún problema para acceder, no dudes en contactar con nuestro equipo de soporte para recibir ayuda.</p>
                                            <p style='margin: 0;'>Saludos cordiales,</p>
                                            <p style='margin: 0;'><strong>El equipo de Comerzia</strong></p>
                                        </td>
                                    </tr>
                                    <!-- Footer -->
                                    <tr>
                                        <td align='center' style='font-size: 14px; color: #888888; padding: 20px; border-top: 1px solid #e0e0e0;'>
                                            <p style='margin: 0;'>¿Necesitas ayuda? Contacta con nuestro <a href='mailto:soporte@comerzia.com' style='color: #4CAF50; text-decoration: none;'>equipo de soporte</a> o visita nuestro <a href='https://comerzia.com/soporte' style='color: #4CAF50; text-decoration: none;'>Centro de Ayuda</a>.</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </body>
                </html>";


            string cuerpoCorreo = plantilla.Replace("{usuario}", usuario).Replace("{contrasenha}", contrasenha);

            return cuerpoCorreo;
        }
    }
}
