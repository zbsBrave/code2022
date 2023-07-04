package org.example;

import com.google.auto.service.AutoService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 正常情况下，需要在META-INF/services/javax.annotation.processing.Processor文件中指定注解处理器的全限定名
 * 比较麻烦
 * 这里借助google开源的AutoService，自动生成对应的配置文件
 * @author zbs
 * @since 2023/7/4
 */
@SupportedAnnotationTypes({"org.example.AutoResource"})
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcess extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            generateConfigFile();
        }
        return false;
    }

    /**
     * 获取 resources/templates目录下的所有文件路径，写入tmp.cnf
     */
    public void generateConfigFile() {
        try {
            FileObject cnf = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "tmp.cnf");
            File folder = new File(new File(cnf.toUri()).getParentFile(), "templates");
            if (!folder.exists() || folder.isFile()) {
                return;
            }
            URI parent = folder.toURI();
            Set<String> paths = FileUtils.listFiles(folder, FileFileFilter.INSTANCE, TrueFileFilter.TRUE)
                    .stream()
                    .map(f -> parent.relativize(f.toURI()).getPath()).collect(Collectors.toSet());
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(cnf.openOutputStream(), StandardCharsets.UTF_8));) {
                for (String p : paths) {
                    bw.write(p);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
