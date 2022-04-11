import ruamel.yaml 

template_file=open(".github/workflows/metricsTemplate.yml")
load_template_file=ruamel.yaml.round_trip_load(template_file,preserve_quotes=True)
steps_tasks_template_file=load_template_file['jobs']['build']['steps']


FullMetrics_tasks= [
    'checkout',
    'Cache',
    'Set up JDK 1.8' ,
    'Build Gradle',
    'Running Gradle Test',
    'Running Sonarqube scan',
    'Retrieve version',
    'Generate Change logs',
    'Get Change logs from Comment',
    'Tag',
    'Create Release',
    'Delete merged branch',
    ]

Api_tasks=[
    'checkout',
    'Cache',
    'Set up JDK 1.8' ,
    'Build Gradle',
    'Running Gradle Test',
    'Set Up Cloud SDK For Staging',
    'Staging Deployment',
    'Set Up Cloud SDK For Live',
    'Live Deployment'
]
MetricsWorker_tasks=[
    'checkout',
    'Cache',
    'Set up JDK 1.8' ,
    'Build Gradle',
    'Running Gradle Test',
    'Set Up Cloud SDK For Staging',
    'Staging Deployment',
    'Set Up Cloud SDK For Live',
    'Live Deployment'
]
WebApp_tasks=[
    'checkout',
    'Cache',
    'Set up JDK 1.8' ,
    'Cache node modules',
    'Setup node',
    'Build Gradle',
    'Running Gradle Test',
    'Running Npm Build'
    'Set Up Cloud SDK For Staging',
    'Staging Deployment',
    'Set Up Cloud SDK For Live',
    'Live Deployment'
]

required_workflow_files = ['Api','MetricsWorker','WebApp','FullMetrics']

def generateFiles(tasks,Route,steps_tasks,load_template_file):
    new_tasks_names=[]
    for name in tasks:
        for i in range(len(steps_tasks)):
            if ('name' in steps_tasks[i] and steps_tasks[i]['name'] == name):
                new_tasks_names.append(steps_tasks[i])
        

    load_template_file['jobs']['build']['steps']=new_tasks_names

    with open(r'.github/workflows/{}.yml'.format(Route),'w') as file:
        documents = ruamel.yaml.round_trip_dump(load_template_file, file,default_flow_style=False)


#We use generateFiles function to generate the workflow files

generateFiles(Api_tasks,'Api',steps_tasks_template_file,load_template_file)
generateFiles(WebApp_tasks,'WebApp',steps_tasks_template_file,load_template_file)
generateFiles(MetricsWorker_tasks,'MetricsWorker',steps_tasks_template_file,load_template_file)
generateFiles(FullMetrics_tasks,'FullMetrics',steps_tasks_template_file,load_template_file)

def definingRoute(Route,first,second):

    with open('.github/workflows/{}.yml'.format(Route), "r+") as f:
        contents = f.read()
        f.seek(0)
        f.write(contents.replace(first,second))
        f.truncate()
        

#Route configuration for each workflow
for Route in required_workflow_files:

    definingRoute(Route,'ROUTE',Route)