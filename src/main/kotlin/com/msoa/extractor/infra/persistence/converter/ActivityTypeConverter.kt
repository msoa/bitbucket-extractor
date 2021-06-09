package com.msoa.extractor.infra.persistence.converter

import com.msoa.extractor.infra.persistence.entity.ActivityType
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class ActivityTypeConverter : AttributeConverter<ActivityType, String> {
    override fun convertToDatabaseColumn(activityType: ActivityType): String {
        return activityType.name
    }

    override fun convertToEntityAttribute(value: String?): ActivityType? {
        return value?.let { ActivityType.valueOf(it) }
    }
}
