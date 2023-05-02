import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

import controllers._

object Main {

  def main(args: Array[String]): Unit = {
    val server = new Server(8080)

    val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")

    server.setHandler(context)
    context.addServlet(new ServletHolder(classOf[Controller]), "/login/*")
    context.addServlet(new ServletHolder(classOf[Controller]), "/signup/*")
    context.addServlet(new ServletHolder(classOf[Controller]), "/home/*")
    context.addServlet(new ServletHolder(classOf[Controller]), "/search/*")
    context.addServlet(new ServletHolder(classOf[Controller]), "/profile/*")
    context.addServlet(new ServletHolder(classOf[Controller]), "/meals/*")

    server.setHandler(context)
    server.start()
    val classpath = getClass.getResource("/").getPath
    println(classpath)
    //server.join()
  }
}