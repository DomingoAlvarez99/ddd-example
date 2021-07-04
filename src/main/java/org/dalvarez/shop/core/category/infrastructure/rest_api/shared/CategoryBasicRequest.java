package org.dalvarez.shop.core.category.infrastructure.rest_api.shared;

import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.shared.domain.validation.Field;
import org.dalvarez.shop.core.shared.domain.validation.FieldValidator;
import org.dalvarez.shop.core.shared.domain.validation.Validator;

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

    public Category toCategory(final Long id) {
        validate();

        return Category.create(
                id,
                name,
                Category.create(null, null, null, parentUuid),
                parentUuid
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
