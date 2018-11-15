# Ogloba Application Installation

本節說明如何打包與安裝Ogloba Application。



## 打包

如果配置都有按照規範的話只要執行下列gradle指令就可以打包成zip：

```shell
$ ./gradlew distZip
```

打包好的zip會產生在專案目錄下的`build/distributions/solution-product-gateway-x.y.z.zip`, x.y.z為目前在build.script中設定的專案版本。



## 上傳

1. 目前還只能使用手動上傳，使用ftp/sftp等方式將打包好的application傳上去，日後會新增自動上傳

2. 解壓上傳的zip

```shell
# unzip solution-product-gateway-x.y.z.zip
Archive:  solution-product-gateway-1.1.zip
   creating: solution-product-gateway-1.1/
   creating: solution-product-gateway-1.1/bin/
  inflating: solution-product-gateway-1.1/bin/install.groovy
  inflating: solution-product-gateway-1.1/bin/ProgressBar.groovy
   creating: solution-product-gateway-1.1/lib/
  inflating: solution-product-gateway-1.1/lib/solution-product-gateway-1.1.jar
  inflating: solution-product-gateway-1.1/lib/solution-product-gateway-1.1-resource.guide.jar
  inflating: solution-product-gateway-1.1/lib/solution-product-gateway-1.1-resource.javadoc.jar
...下略
```

## 安裝

### `bin/install.groovy`

`install.groovy是Ogloba Application安裝程式，可以 **互動** 或 **非互動** 的方式安裝佈署Ogloba Application

互動式安裝會詢問一些安裝的相關資料，會自動偵測預設值，安裝過程中也可以更改預設值。
非互動式安裝可以直接指使command line options來指定安裝設定值。

以下是install.groovy的help：
```shell
# ./install.groovy -h
usage:
                       install.groovy -h
                       install.groovy -[ldsfbugy] [--root]
                       install.groovy -r -v [version]
Options
 -d,--dist <dist name>           Install distribution with dist name
 -f,--force                      Force to override existed files or
                                 folders and doesn't backup
 -g,--group <group>              Owner group to run the service
 -h,--help                       Show usage information
 -l,--list                       List installed projects of different
                                 versions
 -r,--remove                     Remove project
    --root <Installation root>   Installation root directory
 -s,--service <service name>     Install service with service name
 -u,--user <user>                Owner user to run the service
 -v,--version <version>          Install service with dist version
 -y,--yes                  
```

Options說明如下：

* -d,--dist <dist name>           Install distribution with dist name
  專案名稱，預設會自動偵測ex: solution-product-gateway
* -s,--service <service name>     Install service with service name
  服務名稱，預設與dist name一樣
* -v,--version <version>          Install service with dist version
* -f,--force                      Force to override existed files or folders and doesn't backup
  預設不指定-f下會自動對已安裝的程式進行備份，備份方式是將原程式目錄更名成[project-id].bak.[n]，n 已存在時會遞增
* -g,--group <group>              Owner group to run the service
  用來執行服務的group
* -u,--user <user>                Owner user to run the service
* --root <Installation root>   Installation root directory
  程式安裝的根目錄，預設會在/opt/ogloba下
* -y,--yes

​        非互動模式，Options有給值的就使用沒給值的就採用預設值



### 互動式安裝

執行`install.groovy`進行半自動 **互動式** 安裝(需要root權限)

```shell
# cd solution-product-gateway-x.y.z.zip/bin
# ./install.groovy
Dist name[solution-product-gateway]:
Version[1.1]:
Service name[solution-product-gateway]:
User[root]:
Group[OGLOBA]: ogloba^C[root@dev-postgres bin]# ./install.groovy
Dist name[solution-product-gateway]:
Version[1.1]:
Service name[solution-product-gateway]:
User[root]: ogloba
Group[OGLOBA]: ogloba
Root[/opt/ogloba]:
Force[No]:
isForce: false
Source dir path: /root/solution-product-gateway-1.1
Target dir path: /opt/ogloba/solution-product-gateway-1.1
Start script path: /opt/ogloba/solution-product-gateway-1.1/bin/startup
Log path: /opt/ogloba/logs/solution-product-gateway
Backup /opt/ogloba/solution-product-gateway-1.1 to /opt/ogloba/solution-product-gateway-1.1.bak.2...
Copying files...
[################################################################################] 324/324
Install service solution-product-gateway: link /opt/ogloba/solution-product-gateway-1.1/bin/solution-product-gateway.service -> /usr/lib/systemd/system/solution-product-gateway.service:


[Unit]
Description=solution-product-gateway-1.1
After=syslog.target

[Service]
User=ogloba
Group=ogloba
ExecStart=/opt/ogloba/solution-product-gateway-1.1/bin/startup
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target

Setting log dir
Link /opt/ogloba/logs/solution-product-gateway to /opt/ogloba/solution-product-gateway-1.1/log
Reload systemd daemon...OK
Installation done.
Please execute "systemctl start solution-product-gateway" to run service and "system enable solution-product-gateway" to enable service every reboot
```



### 非互動式安裝

以下是非互動安裝的範例：

```shell
# ./install.groovy -d solution-product-gateway -g ogloba -u ogloba -s solution-product-gateway -v 1.1 -y
isRemove: false
Dist: solution-product-gateway
Version: 1.1
Service: solution-product-gateway
User: ogloba
Group: ogloba
Root: /opt/ogloba
Force: No
isForce: false
Source dir path: /root/solution-product-gateway-1.1
Target dir path: /opt/ogloba/solution-product-gateway-1.1
Start script path: /opt/ogloba/solution-product-gateway-1.1/bin/startup
Log path: /opt/ogloba/logs/solution-product-gateway
Backup /opt/ogloba/solution-product-gateway-1.1 to /opt/ogloba/solution-product-gateway-1.1.bak.3...
Copying files...
[################################################################################] 324/324
Install service solution-product-gateway: link /opt/ogloba/solution-product-gateway-1.1/bin/solution-product-gateway.service -> /usr/lib/systemd/system/solution-product-gateway.service:


[Unit]
Description=solution-product-gateway-1.1
After=syslog.target

[Service]
User=ogloba
Group=ogloba
ExecStart=/opt/ogloba/solution-product-gateway-1.1/bin/startup
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target

Setting log dir
Link /opt/ogloba/logs/solution-product-gateway to /opt/ogloba/solution-product-gateway-1.1/log
Reload systemd daemon...OK
Installation done.
Please execute "systemctl start solution-product-gateway" to run service and "system enable solution-product-gateway" to enable service every reboot
```



## 安裝過程說明

1. 程式被安裝到`/opt/ogloba/olution-product-gateway.x.y.z`

2. service描述檔被產出到`bin/solution-product-gateway.service`並link到/usr/lib/systemd/system/solution-product-gateway.service以供系統讀取
3. 建立log目錄並link到`/opt/ogloba/olution-product-gateway.x.y.z/log`
4. Reload service