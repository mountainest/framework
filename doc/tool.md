## windows
```shell
curl -H "Content-Type:application/json" -X POST -d 'json data' URL

# websocket
curl -i -N -H "Connection: Upgrade" -H "Upgrade: websocket"  https://test.com/test --http1.1 --output - -v

# 查看被占用端口对应的 PID
netstat -aon|findstr "8080"
# 结束指定pid的进程
taskkill /T /F /PID 9088

# 重启资源管理器
taskkill /f /im exp
方法1. 重启
下面两种方法不重启
方法2.  打开cmd，输入命令 set PATH=c，这个命令是使你写在path中的变量立即生效，然后重启cmd验证，补充（echo %PATH%可查看系统环境变量）
方法3. 注意即便系统环境变量生效了，若你之前打开的应用程序没关掉重启（在你没重启电脑的情况下），那你这个应用程序也可能读取不到该系统变量（哈哈，就是这么苟），所以你关掉重启该应用就好了
```

## wsl
```shell
# 1、以管理员身份进入命令行窗口，然后执行：
wsl --install
# 2、重启计算机
# 3、安装ubuntu。
wsl --install -d Ubuntu
# 查看linux版本信息：
lsb_release -a
# 查看ip信息
hostname -I
# 切换到普通用户abc
su abc
```


#### 退出telnet：
1、按 ctrl + ]  
2、输入 quit

## k8s
安装docker desktop
安装完docker之后，记得修改为国内镜像源，否则会有类似error [internal] load metadata for docker.io/library/centos:latest的错误。
在daemon.json文件:
```json
	"registry-mirrors" : [
		"https://registry.docker-cn.com",
		"https://docker.mirrors.ustc.edu.cn",
		"http://hub-mirror.c.163.com",
		"https://cr.console.aliyun.com/"
	]
```

```shell
# 使用-t指定镜像名称
docker build -t helloworld .
# 查看镜像列表
docker image ls
# 启动容易，可以通过 -p 指定端口映射，把容器内的80端口映射为宿主机的9001端口
docker run -p 9001:80 helloworld
# 宿主机内查看正在运行的容器
docker ps
# 查看容器内的标准输出
docker logs {containerID}
# 停止容器
docker stop {containerID}

```
可以通过运行 exit 命令或者使用 CTRL+D 来退出容器。

使用 kind （Kubernetes In Docker，就是将 K8s 所需要的所有组件，全部部署在一个docker容器中，是一套开箱即用的 K8s 环境搭建方案）搭建的集群无法在生产中使用。
## IDEA快捷键
清理无用的import：Ctrl + Alt + O

## git
```shell
# git config的作用范围由小到大：local(当前项目，默认) < global（当前用户） < system（所有用户，全局）
git config (--local 默认)
git config --global
git config --system
# 关闭ssl
git config http.sslVerify false
# 禁止快进式提交 https://blog.csdn.net/zombres/article/details/82179122
git merge --no-ff feature

```