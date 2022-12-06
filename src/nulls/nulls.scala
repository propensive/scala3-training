package nulls

@main
def run(): Unit =
  val pwd = Option(System.getenv("PWD")).getOrElse(System.getProperty("user.home"))
  System.out.nn.println(s"Your working directory is $pwd")

