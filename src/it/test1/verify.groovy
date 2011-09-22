import static org.junit.Assert.*


def javaFileName = "target/generated-sources/solitaio/example/Hello.java";
def classFileName = "target/classes/example/Hello.class";

assertTrue("File " + javaFileName + " was not generated", new File(basedir,javaFileName).exists())
assertTrue("Class " + classFileName + " was not generated", new File(basedir,classFileName).exists())

URL url = new File(basedir, "target/classes").toURI().toURL()

def loader = new URLClassLoader([url].toArray(new URL[0]))
def hello = loader.loadClass("example.Hello").newInstance()
assertEquals("Welcome", hello.getGreeting())