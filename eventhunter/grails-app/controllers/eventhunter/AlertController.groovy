package eventhunter

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AlertController {

    AlertService alertService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond alertService.list(params), model:[alertCount: alertService.count()]
    }

    def show(Long id) {
        respond alertService.get(id)
    }

    def create() {
        respond new Alert(params)
    }

    def save(Alert alert) {
        if (alert == null) {
            notFound()
            return
        }

        try {
            alertService.save(alert)
        } catch (ValidationException e) {
            respond alert.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alert.label', default: 'Alert'), alert.id])
                redirect alert
            }
            '*' { respond alert, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond alertService.get(id)
    }

    def update(Alert alert) {
        if (alert == null) {
            notFound()
            return
        }

        try {
            alertService.save(alert)
        } catch (ValidationException e) {
            respond alert.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'alert.label', default: 'Alert'), alert.id])
                redirect alert
            }
            '*'{ respond alert, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        alertService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'alert.label', default: 'Alert'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
