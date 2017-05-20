using System.Linq;

using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.DL.DataLayerContract
{
    public interface IDataAccess<T>
        where T : IEntity
    {
        IQueryable<T> Read();
        void Add(T entity);
        void Update(T entity);
        void Delete(T entity);
    }
}
