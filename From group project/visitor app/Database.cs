using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using VisitorRegistrationApp.Models;

namespace VisitorRegistrationApp.Logic
{
    class Database
    {
        private string connectionString = "mongodb+srv://admin:admin@cluster0.8zpfw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        private readonly IMongoDatabase database;
        private readonly MongoClient dbClient;


        //collection names
        private const string visitorCollection = "Visitor";
        private const string managerCollection = "Manager";
        private const string appointmentCollection = "Appointment";


        public Database(string connectionString)
        {
            this.connectionString = connectionString;
            Logger.Info($"Connecting to database...");
            dbClient = new MongoClient(connectionString);
            database = dbClient.GetDatabase("VisitorApp");
            Logger.Info($"Connection successful");
        }

        public void SaveVisitor(Visitor visitor)
        {
            Logger.Info($"Saving visitor: {visitor.Name}");
            InsertRecord(visitorCollection, visitor);
        }
        public void SaveManager(Manager manager)
        {
            Logger.Info($"Saving manager: {manager.Name}");
            InsertRecord(managerCollection, manager);
        }
        public void SaveAppointment(Appointment appointment)
        {
            Logger.Info($"Saving appointment with id: {appointment.Id}");
            InsertRecord(appointmentCollection, appointment);
        }
        public List<Visitor> GetAllVisitors()
        {
            return GetAllRecords<Visitor>(visitorCollection);

        }
        public List<Manager> GetAllManagers()
        {
            return GetAllRecords<Manager>(managerCollection);

        }
        public List<Appointment> GetAllAppointments()
        {
            return GetAllRecords<Appointment>(appointmentCollection);
        }

        public Visitor GetVisitorById(Guid id)
        {
            var visitor = GetRecordById<Visitor>(visitorCollection, id);

            return visitor;
        }
        public Manager GetManagerById(Guid id)
        {
            var manager = GetRecordById<Manager>("Manager", id);

            return manager;
        }
        public Appointment GetAppointmentById(Guid id)
        {
            var appointment = GetRecordById<Appointment>(appointmentCollection, id);

            return appointment;
        }

        public void UpdateVisitor(Guid id, Visitor visitor)
        {
            UpsertRecord<Visitor>(visitorCollection, id, visitor);
        }
        public void UpdateManager(Guid id, Manager manager)
        {
            UpsertRecord<Manager>(managerCollection, id, manager);
        }
        public void UpdateAppointment(Guid id, Appointment appointment)
        {
            UpsertRecord<Appointment>(visitorCollection, id, appointment);
        }

        public void DeleteAppointment(Guid id)
        {
            DeleteRecord<Appointment>(appointmentCollection, id);
        }
        private void InsertRecord<T>(string table, T record)
        {
            var collection = database.GetCollection<T>(table);
            collection.InsertOne(record);
        }
        private T GetRecordById<T>(string table, Guid id)
        {
            var collection = database.GetCollection<T>(table);
            var filter = Builders<T>.Filter.Eq("_id", id);

            return collection.Find(filter).First();
        }
        private List<T> GetAllRecords<T>(string table)
        {
            var collection = database.GetCollection<T>(table);

            return collection.Find(new BsonDocument()).ToList();
        }
        //update + insert
        //only update is enabled (IsUpsert) because we have insert method already
        private void UpsertRecord<T>(string table, Guid id, T record)
        {
            var collection = database.GetCollection<T>(table);

            Logger.Warning("Obsolete code");
            collection.ReplaceOne(
                new BsonDocument("_id", id),
                record,
                new ReplaceOptions { IsUpsert = false });
        }
        private void DeleteRecord<T>(string table, Guid id)
        {
            var collection = database.GetCollection<T>(table);
            var filter = Builders<T>.Filter.Eq("_id", id);
            collection.DeleteOne(filter);

        }

        internal void DeleteManager(Guid id)
        {
            DeleteRecord<Manager>(managerCollection, id);
        }

        internal void DeleteVisitor(Guid id)
        {
            DeleteRecord<Visitor>(visitorCollection, id);
        }
    }
}
