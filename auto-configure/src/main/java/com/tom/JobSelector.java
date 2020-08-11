package com.tom;

import com.tom.configuration.EnableJob;
import com.tom.jobs.DoctorJob;
import com.tom.jobs.Job;
import com.tom.jobs.TeacherJob;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author WangTao
 */
public class JobSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String,Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(
                EnableJob.class.getName()
        );
        Job.Type type = (Job.Type) annotationAttributes.get("type");
        // 导入的类名称数组
        String[] importClassNames = new String[0];
        switch (type) {
            case DOCTOR:
                importClassNames = new String[]{DoctorJob.class.getName()};
                break;
            case TEACHER:
                importClassNames = new String[]{TeacherJob.class.getName()};
                break;
            default:
                    importClassNames = new String[]{TeacherJob.class.getName()};
                    break;
        }
        return importClassNames;

    }
}
