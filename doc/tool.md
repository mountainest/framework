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
windows目录下的项目，不能使用wsl的go sdk来运行。
```shell
# 1、以管理员身份进入命令行窗口，然后执行：
wsl --install
# 2、重启计算机
# 3、安装ubuntu。
wsl --install -d Ubuntu
# 重启wsl
wsl --shutdown
# 查看linux版本信息：
lsb_release -a
# 查看ip信息
hostname -I

# Update the repositories and list of the packages available
sudo apt update
# Update the system based on the packages installed > the "-y" will approve the change automatically
sudo apt upgrade -y
# wsl下默认root账号无密码，先设置root账号密码，然后再通过su命令进行切换
sudo passwd root
# 切换到普通用户abc，直接su是切换到root用户
su abc
# 查看内存信息，-m指定单位
free -m
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
# 关闭swap分区，在C:\Users\【你的用户名】目录新增.wslconfig文件，然后重启wsl
[wsl2]
swap=0 # 关闭swap
[network]
generateResolvConf = false # 解决域名解析失败的问题

# 安装kind
curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.14.0/kind-linux-amd64
chmod +x ./kind
sudo mv ./kind /usr/local/bin/kind
# 创建k8s集群
kind create cluster --name wslkind
# 查看集群，kind后紧跟集群名称，即kind-wslkind中间不能带空格
kubectl cluster-info --context kind-wslkind
# 查看node
kubectl get nodes
# 查看kube-system空间内运行的pod
kubectl get pods -n kube-system
# 查看集群的所有资源
kubectl get all --all-namespaces
# 安装dashboard
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.6.0/aio/deploy/recommended.yaml
# 开启代理才能访问http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/
kubectl proxy
# 但是，报错no endpoints available for service \"https:kubernetes-dashboard:\
kubectl get pods --namespace kubernetes-dashboard
kubectl describe pod kubernetes-dashboard-5676d8b865-48srv --namespace kubernetes-dashboardName
# 启动部署
kubectl create -f task_deployment.yaml

# 查看当前可用的api-ver
kubectl api-versions

kubeadm init --config kubeadm.yml
# 启用systemd命令
https://www.cnblogs.com/xxred/p/13258347.html
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