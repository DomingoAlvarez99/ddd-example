package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared;

import org.dalvarez.shop.shop_common.shared.domain.util.CollectionUtils;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.GenericNotEmptyValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.RequestValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shop_core.category.domain.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CategoryBasicRequest<R> extends RequestValidator<R> {

    protected String name;

    protected String parentUuid;

    private CategoryBasicRequest(final Class<R> requestClass) {
        super(requestClass);
    }

    protected CategoryBasicRequest(
            final Class<R> requestClass,
            final String name,
            String parentUuid) {
        this(requestClass);
        this.name = name;
        this.parentUuid = parentUuid;
    }

    private Category toCategory(final String uuid) {
        return Category.of(
                null,
                name,
                Category.of(null, null, null, parentUuid),
                uuid
        );
    }

    public Category validateAndGetRequest() {
        return validateAndGetRequest(null);
    }

    public Category validateAndGetRequest(final String uuid) {
        return validateAndGetRequest(uuid, Collections.emptyList());
    }

    protected Category validateAndGetRequest(final String uuid,
                                             final List<Field<Object>> customFieldValidators) {
        validate(CollectionUtils.concat(getDefaultFieldValidators(), customFieldValidators));

        return toCategory(uuid);
    }

    protected List<Field<Object>> getDefaultFieldValidators() {
        final List<Field<Object>> fields = new ArrayList<>();
        fields.add(new Field<>(FieldNames.NAME, name, GenericNotEmptyValidator.getInstance()));

        if (parentUuid != null)
            fields.add(new Field<>(FieldNames.PARENT_UUID, parentUuid, UuidValidator.getInstance()));

        return fields;
    }

    public String getName() {
        return name;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public static class FieldNames {

        public static final String NAME = "name";

        public static final String PARENT_UUID = "parentUuid";

    }

}
