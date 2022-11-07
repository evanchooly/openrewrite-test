package dev.morphia.test.aggregation.experimental.expressions;

import java.time.LocalDate;

import dev.morphia.aggregation.experimental.expressions.impls.Expression;
import dev.morphia.aggregation.experimental.expressions.impls.MathExpression;
import dev.morphia.aggregation.experimental.stages.Projection;
import dev.morphia.mapping.codec.writer.DocumentWriter;
import dev.morphia.test.TestBase;
import dev.morphia.test.models.User;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.EncoderContext;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

public class ExpressionsTestBase extends TestBase {
    @BeforeMethod
    public void seed() {
        getMapper().getCollection(User.class).drop();
        getDs().save(new User("", LocalDate.now()));
    }

    @SuppressWarnings("unchecked")
    protected void assertAndCheckDocShape(String expectedString, Expression value, Object expectedValue) {
        Document expected = Document.parse(expectedString);
        DocumentWriter writer = new DocumentWriter(getMapper());
        ((Codec) getMapper().getCodecRegistry()
                .get(MathExpression.class))
                        .encode(writer, value, EncoderContext.builder().build());
        Document actual = writer.getDocument();
        assertDocumentEquals(actual, expected);

        Document test = getDs().aggregate(User.class)
                .project(Projection.project()
                        .include("test", value))
                .execute(Document.class)
                .next();
        assertEquals(test.get("test"), expectedValue);
    }
}
