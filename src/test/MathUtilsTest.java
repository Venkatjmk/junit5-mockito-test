import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import maths.MathUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Simple Calculator Test Class")
class MathUtilsTest {

	MathUtils mathUtils = null;
	
	@BeforeAll
	void befoerAll() {
		System.out.println("before all");
	}
	
	@AfterAll
	void afterAll() {
		System.out.println("after all");
	}
	
	@BeforeEach
	void init() {
		System.out.println("Initialize mathUtils");
		mathUtils = new MathUtils();
	}
	
	@AfterEach
	void destroy() {
		System.out.println("cleaning up mathUtils");
		mathUtils = null;
	}
	
	@Test
	@DisplayName("This test is disabled")
	@Disabled()
	void testDisabled() {
		fail("this tes is failed");
	}
	
	@Test
	@DisplayName("Adding 2 numbers")
	@Tag("Math")
	void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, () -> "The method should add 2 numbers");//3rd arg lambda gets executed only when the test is failed
	}
	
	@Nested
	@Tag("Math")
	class TestSubtract {
		@Test
		@DisplayName("Subtracting all positive numbers")
		void testSubtractAllPositive() {
			assertEquals(0, mathUtils.subtract(2, 2), "The method should subtract given numbers");
		}
		
		@Test
		@DisplayName("Subtracting all negative numbers")
		void testSubtractAllNegative() {
			assertEquals(0, mathUtils.subtract(-2, -2), "The method should subtract given numbers");
		}
		
		@Test
		@DisplayName("Subtracting combination of positive and negative numbers")
		void testSubtractCombo() {
			assertEquals(-4, mathUtils.subtract(-2, 2), "The method should subtract given numbers");
		}
		
	}

	@Test
	@DisplayName("Multiplying 2 numbers")
	@Tag("Math")
	void testMultiply() {
//		assertEquals(4, mathUtils.multiply(2, 2), "This should return product");
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2, 2)),
				() -> assertEquals(4, mathUtils.multiply(-2, -2)),
				() -> assertEquals(-4, mathUtils.multiply(2, -2)),
				() -> assertEquals(-4, mathUtils.multiply(-2, 2)),
				() -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(0, mathUtils.multiply(0, 2))
				);
	}
	
	@Test
	@DisplayName("Denominator 0 thorws Exception heck test")
	@EnabledOnOs(OS.LINUX)
	@Tag("Math")
	void testDivide() {
		assertThrows(ArithmeticException.class, ()->mathUtils.divide(10,0), "This method should throw exception on denominator 0");
	}
	
	@RepeatedTest(3)
	@Tag("Circle")
	void testComputeCircleArea(RepetitionInfo reptInfo) {
//		reptInfo.
		double result = mathUtils.computeCircleArea(10);
		assertEquals(Math.PI*100, result, "Circle formula is pi*r*r");
	}

}
