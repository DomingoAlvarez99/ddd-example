package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.controller.put;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.IdValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared.CategoryBasicRequest;

import java.util.List;
import java.util.Map;

public class CategoryPutRequest extends CategoryBasicRequest<CategoryPutRequest> {

    private static final Map<String, FieldValidator> fieldsValidators = Map.of(
            FieldNames.UUID, IdValidator.getInstance(),
            CategoryBasicRequest.FieldNames.NAME, GenericNotEmptyValidator.getInstance(),
            CategoryBasicRequest.FieldNames.PARENT_UUID, UuidValidator.getInstance()
    );

    private String uuid;

    public CategoryPutRequest(
            final String name,
            final String parentUuid) {
        super(
                fieldsValidators,
                CategoryPutRequest.class,
                name,
                parentUuid
        );
    }

    @Override
    public Category toCategory(final String uuid) {
        this.uuid = uuid;

        return super.toCategory(uuid);
    }

    @Override
    protected List<Field<Object>> getFields() {
        return List.of(
                new Field<>(FieldNames.UUID, uuid),
                new Field<>(CategoryBasicRequest.FieldNames.NAME, name),
                new Field<>(CategoryBasicRequest.FieldNames.PARENT_UUID, parentUuid)
        );
    }

    @Override
    public String toString() {
        return "CategoryPutRequest{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                '}';
    }

    public static class FieldNames {

        public static final String UUID = "uuid";

    }

}
