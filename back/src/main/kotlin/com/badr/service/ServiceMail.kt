package com.badr.service

import com.badr.utils.Constantes
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

object ServiceMail {
    fun sendMail(sendtTo:String, message:String){
        val email = SimpleEmail()
        email.hostName = Constantes.hostSMTP
        email.setSmtpPort(Constantes.portSMTP)
        email.setAuthenticator(DefaultAuthenticator(Constantes.userAuthSMTP, Constantes.passAuthSMTP))
        email.isSSLOnConnect = true
        email.setFrom(Constantes.sendFrom)
        email.subject = "Hobbyte"
        email.setMsg(message)
        email.addTo(sendtTo)

        email.setContent(message, "text/html")
        try {
            email.send()
        } catch (e: Exception) {
            println("Error al enviar el correo: ${e.message}")
        }
    }
}