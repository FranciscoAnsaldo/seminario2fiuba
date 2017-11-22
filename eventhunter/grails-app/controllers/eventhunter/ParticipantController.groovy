package eventhunter

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ParticipantController {

    ParticipantService participantService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond participantService.list(params), model:[participantCount: participantService.count()]
    }

    def show(Long id) {
        respond participantService.get(id)
    }

    def create() {
        respond new Participant(params)
    }

    def save(Participant participant) {
        if (participant == null) {
            notFound()
            return
        }

        try {
            participantService.save(participant)
        } catch (ValidationException e) {
            respond participant.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'participant.label', default: 'Participant'), participant.id])
                redirect participant
            }
            '*' { respond participant, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond participantService.get(id)
    }

    def update(Participant participant) {
        if (participant == null) {
            notFound()
            return
        }

        try {
            participantService.save(participant)
        } catch (ValidationException e) {
            respond participant.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'participant.label', default: 'Participant'), participant.id])
                redirect participant
            }
            '*'{ respond participant, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        participantService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'participant.label', default: 'Participant'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'participant.label', default: 'Participant'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
