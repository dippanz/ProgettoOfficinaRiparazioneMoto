package it.officina.OfficinaRiparazioneMoto.shared;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class ErrorManager {
        public static final String USERNAME_ALREADY_EXISTS = "00001";
        public static final String EMAIL_ALREADY_EXISTS = "00002";

        private static final Map<String, String> errorCodes = Map.of(
            USERNAME_ALREADY_EXISTS, "Username già esistente",
            EMAIL_ALREADY_EXISTS, "Email già esistente"
        );

        public static String getErrorMessage(String errorCode){
            return errorCodes.get(errorCode);
        }
    }

    






}
