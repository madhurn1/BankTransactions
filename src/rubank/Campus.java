package rubank;

/**
 Enum class representing a campus.
 Can be one of 3 campuses.
 @author Dany Chucri, Madhur Nutulapati
 */
public enum Campus {
        NEW_BRUNSWICK(0),
        NEWARK(1),
        CAMDEN(2);

        private final int value;

        Campus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
}
