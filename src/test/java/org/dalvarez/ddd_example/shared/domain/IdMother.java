package org.dalvarez.ddd_example.shared.domain;

import org.dalvarez.ddd_example.shared.domain.util.RandomElementPicker;

public final class IdMother {

    public static String randomPick() {
        return RandomElementPicker.from(
                "9ee0e29d-3438-43b2-a815-b176b98753e0",
                "439699c8-6133-4733-a743-6ccd7ee74fc8",
                "5fbf0bb9-f7d5-4b6b-a740-dd2bd93daa79",
                "ceaa30c9-9be1-4c72-b2a2-7f5c75316fef",
                "72b97c80-8773-47e9-8a19-6c6b03278f53",
                "f89929ad-fc8d-427e-8da0-1f35ced93625",
                "b1c262c0-d83a-4e28-8694-155f33f31ea2",
                "f21f9665-cefe-4e2f-92c7-45f94768378c",
                "3e964dc5-14b9-422b-9a8c-5b1388ebfcd4",
                "359ea480-f332-4113-b3d5-399023666399",
                "c4dc952f-b16a-4cad-98ac-3bfa2abe5ef4",
                "b570f868-aa5f-4269-8b0d-07cfa7cf4865"
        );
    }

    public static String randomGeneration(int idx) {
        return "b570f868-aa5f-4269-8b0d-07cfa7cf4" + String.format("%03d", idx);
    }

}
