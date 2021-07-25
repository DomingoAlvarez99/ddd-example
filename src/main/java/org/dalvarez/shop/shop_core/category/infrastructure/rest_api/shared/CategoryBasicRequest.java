package org.dalvarez.shop.shop_core.category.infrastructure.rest_api.shared;

import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shop_common.shared.infrastructure.validation.Validator;
import org.dalvarez.shop.shop_core.category.domain.Category;

import java.util.List;
import java.util.Map;

public abstract class CategoryBasicRequest<R> extends Validator<R> {

    protected String name;

    protected String parentUuid;

    private CategoryBasicRequest(final Map<String, FieldValidator> fieldsValidators,
                                 final Class<R> requestClass) {
        super(fieldsValidators, requestClass);
    }

    protected CategoryBasicRequest(
            final Map<String, FieldValidator> fieldsValidators,
            final Class<R> requestClass,
            final String name,
            String parentUuid) {
        this(fieldsValidators, requestClass);
        this.name = name;
        this.parentUuid = parentUuid;
    }

    @Override
    protected abstract List<Field<Object>> getFields();

    public Category toCategory() {
        return toCategory(null);
    }

    public Category toCategory(final String uuid) {
        validate();

        return Category.create(
                null,
                name,
                Category.create(null, null, null, parentUuid),
                uuid
        );
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
