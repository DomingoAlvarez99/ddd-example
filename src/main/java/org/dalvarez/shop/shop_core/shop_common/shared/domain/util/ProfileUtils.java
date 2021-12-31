package org.dalvarez.shop.shop_core.shop_common.shared.domain.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class ProfileUtils {

    @Value("${spring.profiles.active}")
    private String active;

    public String getActive() {
        return active;
    }

    public Boolean isDevActive() {
        return active.equalsIgnoreCase(ProfileType.DEV.getValue());
    }

    public Boolean isDefaultActive() {
        return active.equalsIgnoreCase(ProfileType.DEFAULT.getValue());

    }

    public Boolean isTestActive() {
        return active.equalsIgnoreCase(ProfileType.TEST.getValue());
    }

}
