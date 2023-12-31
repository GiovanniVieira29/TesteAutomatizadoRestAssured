package TesteAPI;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;;


public class SoapXmlRequest {
	
	@Test
	public void ValidacaoSoapXML () throws IOException  {
		
		File file = new File("./SoapRequest/Add.xml.txt");
		
		if(file.exists())
			System.out.println("  >> File Exists ");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		baseURI = "https://ecs.syr.edu/";
		
		given().
		contentType("text/xml").
		accept(ContentType.XML).
		body(requestBody).
		when().
		post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx?op=Add").
        then().
        statusCode(200).
        log().all().
        and().
        body("//*:AddResult.text()", equalTo("5"));
	}

}
