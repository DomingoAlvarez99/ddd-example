package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.post;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared.CategoryBasicRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryPostRequest extends CategoryBasicRequest<CategoryPostRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            FieldNames.PARENT_UUID, UuidValidator.getInstance()
    );

    public CategoryPostRequest(final String name,
                               final String parentUuid) {
        super(
                fieldsValidators,
                CategoryPostRequest.class,
                name,
                parentUuid
        );
    }

    @Override
    protected List<Field<Object>> getFields() {
        final List<Field<Object>> fields = new ArrayList<>();
        fields.add(new Field<>(FieldNames.NAME, name));

        if (parentUuid != null)
            fields.add(new Field<>(FieldNames.PARENT_UUID, parentUuid));

        return fields;
    }

}
