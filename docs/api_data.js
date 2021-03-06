define({ "api": [
  {
    "type": "get",
    "url": "/version",
    "title": "Version",
    "name": "Version",
    "group": "devops",
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/devops/DevOpsController.kt",
    "groupTitle": "devops"
  },
  {
    "type": "get",
    "url": "/project/",
    "title": "Get all projects",
    "name": "All_Projects",
    "group": "project",
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "project"
  },
  {
    "type": "post",
    "url": "/project/{projectId}/type/",
    "title": "Create new script type (sketch, song, etc)",
    "name": "Create_new_script_type_(sketch,_song,_etc)",
    "group": "project",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "project"
  },
  {
    "type": "post",
    "url": "/project/",
    "title": "Create new project",
    "name": "Create_project",
    "group": "project",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "description",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "project"
  },
  {
    "type": "get",
    "url": "/project/{{projectId}}",
    "title": "Get project by id",
    "name": "Project",
    "group": "project",
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "project"
  },
  {
    "type": "put",
    "url": "/{projectId}/script/{scriptId}/description",
    "title": "Change description of script",
    "name": "Change_description_of_script",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "description",
            "description": ""
          }
        ]
      }
    },
    "group": "script",
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "script"
  },
  {
    "type": "put",
    "url": "/{projectId}/script/{scriptId}/name",
    "title": "Change name of script",
    "name": "Change_name_of_script",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": ""
          }
        ]
      }
    },
    "group": "script",
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "script"
  },
  {
    "type": "post",
    "url": "/project/{{projectId}}/script/{{scriptId}}/line/",
    "title": "Create new line in script",
    "name": "Create_new_line_in_script",
    "group": "script",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>Type of line - REMARK or ACTION</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "text",
            "description": "<p>What's being said</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "roleId",
            "description": "<p>Id of the role performing the line</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "ordering",
            "description": "<p>Placement of the line, if unset, added to end of script</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "script"
  },
  {
    "type": "post",
    "url": "/project/{{projectId}}/script/{{scriptId}}/role/",
    "title": "Create new role in script",
    "name": "Create_new_role_for_script",
    "group": "script",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "role",
            "description": "<p>Name of role</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "actorId",
            "description": "<p>Id of the actor</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "description",
            "description": "<p>Describe the role (costumes and so on)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "script"
  },
  {
    "type": "post",
    "url": "/project/{{projectId}}/script/",
    "title": "Create new script",
    "name": "Create_new_script",
    "group": "script",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>Name of script</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "typeId",
            "description": "<p>Id of type (id of sketch, song, etc)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "description",
            "description": "<p>Description of script</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "script"
  },
  {
    "type": "get",
    "url": "/project/{{projectId}}/script/{{scriptId}}",
    "title": "Get script by id",
    "name": "Get_all_scripts",
    "group": "script",
    "version": "0.0.0",
    "filename": "src/main/kotlin/com/settraces/settracesbackend/project/controllers/ProjectController.kt",
    "groupTitle": "script"
  }
] });
