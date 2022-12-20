# 动力商城
## 搭建父工程
* pom.xml


    <properties>
        <!-- 版本控制  druid mybatis-plus admin  fastjson  hutool  swaggerui  fastdfs  -->
        <java.version>1.8</java.version>
        <spring-boot.version>2.3.12.RELEASE</spring-boot.version>
        <spring-cloud.version>Hoxton.SR12</spring-cloud.version>
        <spring-cloud-alibaba.version>2.2.7.RELEASE</spring-cloud-alibaba.version>
        <spring-boot-admin.version>2.3.0</spring-boot-admin.version>
        <mybatis-plus.version>3.4.0</mybatis-plus.version>
        <druid.starter.version>1.2.4</druid.starter.version>
        <swagger.version>3.0.0</swagger.version>
        <hutool.version>5.3.9</hutool.version>
        <fastDFS.version>1.27.2</fastDFS.version>
        <fastJson.version>1.2.73</fastJson.version>
    </properties>

    <!-- 全局依赖 lombok hutool  fastJson   mybatis  注解相关的依赖  @TableField  -->
    <dependencies>
        <!--健康检查-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-annotation</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${hutool.version}</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastJson.version}</version>
        </dependency>
    </dependencies>

    <!-- 依赖管理  cloud  admin  myabtis-plus  druid fastdfs   -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>de.codecentric</groupId>
                <artifactId>spring-boot-admin-dependencies</artifactId>
                <version>${spring-boot-admin.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--  引入druid starter-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>${druid.starter.version}</version>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus.version}</version>
            </dependency>
            <!--            <dependency>-->
            <!--                <groupId>com.github.tobato</groupId>-->
            <!--                <artifactId>fastdfs-client</artifactId>-->
            <!--                <version>${fastDFS.version}</version>-->
            <!--            </dependency>-->
        </dependencies>
    </dependencyManagement>

## 搭建iaas工程
* pom.xml

