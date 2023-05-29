package preexamen3test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import preexamen3.Auth;
import preexamen3.RolesAuth;
import preexamen3.ValidacionAuth;

public class AuthTest {

    private Auth auth;
    private MockedStatic<ValidacionAuth> overrideValidacionAuthMock;
    private MockedStatic<RolesAuth> overrideRolesAuthMock;

    @BeforeEach
    public void setup() {
        auth = new Auth();
        overrideValidacionAuthMock = Mockito.mockStatic(ValidacionAuth.class);
        overrideRolesAuthMock = Mockito.mockStatic(RolesAuth.class);

    }

    @AfterEach
    public void cleanup() {
        overrideValidacionAuthMock.close();
        overrideRolesAuthMock.close();
    }

    @ParameterizedTest
    @CsvSource({
            "Argos,123,INCORRECT Argos AND 123",
            "Marcos,holaBuenas,PERMISSION ROLE : C",
            "Dalas,ufa,PERMISSION ROLE : R",
            "Javi,321,PERMISSION ROLE : U",
            "Marlos,jaja,PERMISSION ROLE : D",
            "Paco,ocap,PERMISSION ROLE : CR",
            "Pole,pole123,PERMISSION ROLE : CU",
            "Daniel,dnl1,PERMISSION ROLE : CD",
            "Nauter,ghs,PERMISSION ROLE : RU",
            "German,hsgg,PERMISSION ROLE : RD",
            "Trevor,trk,PERMISSION ROLE : UD",
            "Guitar,ghs321,PERMISSION ROLE : CRU",
            "Guibel, bsdisimo,PERMISSION ROLE : CRD",
            "Souls,slu99,PERMISSION ROLE : RUD",
            "Bops,gips,PERMISSION ROLE : CRUD"
    })
    public void verifyAuth(String user, String pwd, String expectedResult) {
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Argos","123")).thenReturn(false);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Marcos","holaBuenas")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Dalas","ufa")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Javi","321")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Marlos","jaja")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Paco","ocap")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Pole","pole123")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Daniel","dnl1")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Nauter","ghs")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("German","hsgg")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Trevor","trk")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Guitar","ghs321")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Guibel","bsdisimo")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Souls","slu99")).thenReturn(true);
        overrideValidacionAuthMock.when(() -> ValidacionAuth.isAuthValid("Bops","gips")).thenReturn(true);

        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Marcos","holaBuenas")).thenReturn("C");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Dalas","ufa")).thenReturn("R");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Javi","321")).thenReturn("U");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Marlos","jaja")).thenReturn("D");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Paco","ocap")).thenReturn("CR");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Pole","pole123")).thenReturn("CU");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Daniel","dnl1")).thenReturn("CD");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Nauter","ghs")).thenReturn("RU");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("German","hsgg")).thenReturn("RD");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Trevor","trk")).thenReturn("UD");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Guitar","ghs321")).thenReturn("CRU");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Guibel","bsdisimo")).thenReturn("CRD");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Souls","slu99")).thenReturn("RUD");
        overrideValidacionAuthMock.when(() -> RolesAuth.getRoles("Bops","gips")).thenReturn("CRUD");
        String actualResult = auth.auth(user, pwd);
        Assertions.assertTrue(actualResult.contains(expectedResult));



    }

}
