package eventhunter

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AlertListController {

    AlertListService alertListService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond alertListService.list(params), model:[alertListCount: alertListService.count()]
    }

    def show(Long id) {
        respond alertListService.get(id)
    }

    def create() {
        respond new AlertList(params)
    }

    def save(AlertList alertList) {
        if (alertList == null) {
            notFound()
            return
        }

        try {
            alertListService.save(alertList)
        } catch (ValidationException e) {
            respond alertList.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alertList.label', default: 'AlertList'), alertList.id])
                redirect alertList
            }
            '*' { respond alertList, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond alertListService.get(id)
    }

    def update(AlertList alertList) {
        if (alertList == null) {
            notFound()
            return
        }

        try {
            alertListService.save(alertList)
        } catch (ValidationException e) {
            respond alertList.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'alertList.label', default: 'AlertList'), alertList.id])
                redirect alertList
            }
            '*'{ respond alertList, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        alertListService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'alertList.label', default: 'AlertList'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alertList.label', default: 'AlertList'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
