package fi.solita.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringOutputStream;

/**
 * Says "Hi" to the user.
 * @goal sayhi
 * @phase generate-sources
 */
public class GreetingMojo extends AbstractMojo
{
    /**
     * The greeting to display.
     *
     * @parameter expression="${sayhi.greeting}" default-value="Hello World!"
     */
    private String greeting;
    
    /**
     * @parameter default-value="${project.build.directory}/generated-sources/solitaio"
     */
    private File outputDirectory;
    
    /** @parameter default-value="${project}" */
    private MavenProject project;
    
    public void execute() throws MojoExecutionException
    {
    	try {
    		getLog().info("\n\n\n\n" + greeting + "\n\n\n\n");
        
	        File file = new File(outputDirectory,"example/Hello.java");
	        file.getParentFile().mkdirs();
        
			file.createNewFile();
			
			FileOutputStream out = new FileOutputStream(file);
			OutputStreamWriter outwriter = new OutputStreamWriter(out);
			outwriter.append("package example;class Hello{public String getGreeting(){return \"" + greeting + "\";}}");
			
			outwriter.close();
			out.close();
			
			project.addCompileSourceRoot(outputDirectory.getAbsolutePath() );
		} catch (IOException e) {
			throw new MojoExecutionException("Ei toimi", e);
		}
        
    }
}

