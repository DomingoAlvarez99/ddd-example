package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared.CategoryBasicRequest;

import java.util.List;

public class CategoryPutRequest extends CategoryBasicRequest<CategoryPutRequest> {

    public CategoryPutRequest(
            final String name,
            final String parentUuid) {
        super(
                CategoryPutRequest.class,
                name,
                parentUuid
        );
    }

    @Override
    public Category validateAndGetRequest(final String uuid) {
        return super.validateAndGetRequest(uuid, customFieldValidators(uuid));
    }

    protected List<Field<Object>> customFieldValidators(final String uuid) {
        return List.of(new Field<>(CategoryPutRequest.FieldNames.UUID, uuid, UuidValidator.getInstance()));
    }

    public static class FieldNames {

        public static final String UUID = "uuid";

    }

}
