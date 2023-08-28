package com.mincrm;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MybatisGenerator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        InputStream is = MybatisGenerator.class.getClassLoader().getResourceAsStream("generatorConfig.xml");

        ConfigurationParser cfgPaser = new ConfigurationParser(warnings);
        Configuration config = cfgPaser.parseConfiguration(is);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        Set<String> tables = new HashSet();
//		tables.add("user");
//		tables.add("user");
        myBatisGenerator.generate(null, null, null);

        for (String line : warnings) {
            System.err.println(line);
        }

    }
/*


  */
}
